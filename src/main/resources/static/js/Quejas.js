const modal = document.getElementById('detalleQuejaModal');
const closeBtn = document.getElementById('closeQuejaModal');
const cancelBtn = document.getElementById('cancelQuejaModal');
const viewButtons = document.querySelectorAll('button[title="Ver detalles"]');

viewButtons.forEach(btn => {
    btn.addEventListener('click', () => modal.classList.remove('hidden'));
});

if (closeBtn) closeBtn.addEventListener('click', () => modal.classList.add('hidden'));
if (cancelBtn) cancelBtn.addEventListener('click', () => modal.classList.add('hidden'));

window.addEventListener('click', (e) => {
    if (e.target === modal) modal.classList.add('hidden');
});
