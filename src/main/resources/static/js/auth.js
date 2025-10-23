document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('loginForm');
    const errorBox = document.getElementById('loginError');

    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        if (errorBox) { errorBox.textContent = ''; errorBox.classList.add('hidden'); }

        const email = form.elements['email'].value.trim();
        const password = form.elements['password'].value;

        try {
            const res = await fetch('/auth/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, password })
            });

            if (!res.ok) {
                let msg = 'Login falló';
                try {
                    const data = await res.json();
                    if (data.error) msg = data.error;
                } catch (_) {}
                throw new Error(msg);
            }

            const data = await res.json(); // { token, rol }
            localStorage.setItem('token', data.token);
            localStorage.setItem('rol', data.rol);

            // Redirige según rol
            window.location.href = (data.rol === 'ADMIN') ? '/admin' : '/cliente';
        } catch (err) {
            if (errorBox) { errorBox.textContent = err.message; errorBox.classList.remove('hidden'); }
            else alert(err.message);
        }
    });
});

// Helper para futuras peticiones autenticadas
window.authFetch = async (url, options = {}) => {
    const token = localStorage.getItem('token');
    const headers = new Headers(options.headers || {});
    if (token) headers.set('Authorization', `Bearer ${token}`);
    if (!headers.has('Content-Type') && !(options.body instanceof FormData)) {
        headers.set('Content-Type', 'application/json');
    }
    const res = await fetch(url, { ...options, headers });
    if (res.status === 401) {
        // Token inválido/expirado: vuelve al login
        window.location.href = '/auth';
    }
    return res;
};
