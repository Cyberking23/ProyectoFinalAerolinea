const modal = document.getElementById('avionModal');
const openBtn = document.getElementById('openAvionModal');
const closeBtn = document.getElementById('closeAvionModal');
const cancelBtn = document.getElementById('cancelAvionModal');

if (openBtn) openBtn.addEventListener('click', () => modal.classList.remove('hidden'));
if (closeBtn) closeBtn.addEventListener('click', () => modal.classList.add('hidden'));
if (cancelBtn) cancelBtn.addEventListener('click', () => modal.classList.add('hidden'));

window.addEventListener('click', (e) => {
    if (e.target === modal) modal.classList.add('hidden');
});
