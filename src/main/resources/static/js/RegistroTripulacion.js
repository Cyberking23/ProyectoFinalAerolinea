const modal = document.getElementById('tripModal');
const openBtn = document.getElementById('openTripModal');
const closeBtn = document.getElementById('closeTripModal');
const cancelBtn = document.getElementById('cancelTripModal');

const form = document.getElementById('tripForm');
const title = document.getElementById('tripModalTitle');
const submitBtn = document.getElementById('tripSubmitBtn');

function openModal() { modal.classList.remove('hidden'); }
function closeModal() { modal.classList.add('hidden'); }

openBtn?.addEventListener('click', () => {
    // MODO CREAR
    title.textContent = 'Añadir miembro de tripulación';
    submitBtn.textContent = 'Añadir elemento';
    form.action = '/tripulantes'; // crear
    form.reset();
    document.getElementById('tripId').value = '';
    openModal();
});

closeBtn?.addEventListener('click', closeModal);
cancelBtn?.addEventListener('click', closeModal);
window.addEventListener('click', (e) => { if (e.target === modal) closeModal(); });

// EDITAR
document.querySelectorAll('.editTripBtn').forEach(btn => {
    btn.addEventListener('click', () => {
        const id = btn.dataset.id;
        const nombre = btn.dataset.nombre;
        const rol = btn.dataset.rol;
        const disponibilidad = btn.dataset.disponibilidad;
        const aerolineaId = btn.dataset.aerolineaId;

        title.textContent = 'Editar miembro de tripulación';
        submitBtn.textContent = 'Guardar cambios';
        form.action = `/tripulantes/${id}/actualizar`; // actualizar
        document.getElementById('tripId').value = id;

        // set fields
        form.querySelector('[name="nombre"]').value = nombre;
        form.querySelector('[name="rol"]').value = rol;
        form.querySelector('[name="disponibilidad"]').value = disponibilidad;
        form.querySelector('[name="aerolineaId"]').value = aerolineaId;

        openModal();
    });
});
