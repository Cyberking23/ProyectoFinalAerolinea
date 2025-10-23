// /js/auth.js
(function () {
    // Helpers
    const q = (s) => document.querySelector(s);
    const showMsg = (el, msg, ok = false) => {
        if (!el) return;
        el.textContent = msg || "";
        el.className = ok
            ? "mt-3 text-green-600 text-center text-sm"
            : "mt-3 text-red-600 text-center text-sm";
    };

    // LOGIN
    const loginForm = q("#login-form");
    if (loginForm) {
        const emailEl = q("#login-email");
        const passEl  = q("#login-pass");
        const msgEl   = q("#login-msg");

        loginForm.addEventListener("submit", async (e) => {
            e.preventDefault();
            showMsg(msgEl, "");

            const email = (emailEl.value || "").trim().toLowerCase();
            const password = passEl.value || "";

            try {
                const resp = await fetch("/auth/login", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ email, password }),
                });

                const data = await resp.json();

                if (!resp.ok) {
                    showMsg(msgEl, data.error || "No se pudo iniciar sesión");
                    return;
                }

                // Guardar token + rol
                localStorage.setItem("jwt", data.token);
                localStorage.setItem("rol", data.rol);

                showMsg(msgEl, "¡Login ok, redirigiendo!", true);

                // Redirección simple por rol
                if ((data.rol || "").toUpperCase() === "ADMIN") {
                    window.location.href = "/RegistroAerolinea"; // ajusta a tu dashboard admin
                } else {
                    window.location.href = "/VueloCliente"; // ajusta a tu dashboard cliente
                }
            } catch (err) {
                showMsg(msgEl, "Error de red o servidor");
            }
        });
    }

    // REGISTRO
    const regForm = q("#reg-form");
    if (regForm) {
        const nombreEl = q("#reg-nombre");
        const correoEl = q("#reg-correo");
        const passEl   = q("#reg-pass");
        const repEl    = q("#reg-rep");
        const msgEl    = q("#reg-msg");

        regForm.addEventListener("submit", async (e) => {
            e.preventDefault();
            showMsg(msgEl, "");

            const nombre = (nombreEl.value || "").trim();
            const correo = (correoEl.value || "").trim().toLowerCase();
            const contrasena = passEl.value || "";
            const repetir = repEl.value || "";

            try {
                const resp = await fetch("/auth/registro", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ nombre, correo, contrasena, repetir }),
                });

                const data = await resp.json();
                if (!resp.ok) {
                    showMsg(msgEl, data.error || "No se pudo registrar");
                    return;
                }

                showMsg(msgEl, "¡Registro exitoso! Ahora inicia sesión.", true);
                setTimeout(() => (window.location.href = "/auth"), 1200);
            } catch (err) {
                showMsg(msgEl, "Error de red o servidor");
            }
        });
    }
})();
