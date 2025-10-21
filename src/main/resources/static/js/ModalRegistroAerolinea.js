const modal = document.getElementById('aerolineaModal');
const openBtn = document.getElementById('openModalBtn');
const closeBtn = document.getElementById('closeModalBtn');
const cancelBtn = document.getElementById('cancelBtn');
const form = document.getElementById('aerolineaForm');
const modalTitle = document.getElementById('modalTitle');
const submitBtn = document.getElementById('submitBtn');

function openModal() { modal.classList.remove('hidden'); }
function closeModal() { modal.classList.add('hidden'); }

openBtn?.addEventListener('click', () => {
    // MODO CREAR
    modalTitle.textContent = 'Añadir Aerolínea';
    submitBtn.textContent = 'Añadir elemento';
    form.action = '/aerolineas';        // POST crear
    form.reset();
    document.getElementById('id').value = '';
    openModal();
});

closeBtn?.addEventListener('click', closeModal);
cancelBtn?.addEventListener('click', closeModal);
window.addEventListener('click', (e) => { if (e.target === modal) closeModal(); });

// Botones editar (agarramos todos los .editBtn)
document.querySelectorAll('.editBtn').forEach(btn => {
    btn.addEventListener('click', () => {
        const id = btn.dataset.id;
        const nombre = btn.dataset.nombre;
        const codigo = btn.dataset.codigo;
        const pais = btn.dataset.pais;
        const estado = btn.dataset.estado;

        // MODO EDITAR
        modalTitle.textContent = 'Editar Aerolínea';
        submitBtn.textContent = 'Guardar cambios';
        form.action = `/aerolineas/${id}/actualizar`; // POST actualizar

        // Seteamos campos del form (coinciden con th:field)
        document.getElementById('id').value = id;
        form.querySelector('[name="nombre"]').value = nombre;
        form.querySelector('[name="codigoIataIcao"]').value = codigo;
        form.querySelector('[name="paisOrigen"]').value = pais;
        form.querySelector('[name="estado"]').value = estado;

        openModal();
    });
});
