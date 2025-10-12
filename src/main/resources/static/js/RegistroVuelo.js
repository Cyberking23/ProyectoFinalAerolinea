const modal = document.getElementById('vueloModal');
const openBtn = document.getElementById('openVueloModal');
const closeBtn = document.getElementById('closeVueloModal');
const cancelBtn = document.getElementById('cancelVueloModal');

if (openBtn) openBtn.addEventListener('click', () => modal.classList.remove('hidden'));
if (closeBtn) closeBtn.addEventListener('click', () => modal.classList.add('hidden'));
if (cancelBtn) cancelBtn.addEventListener('click', () => modal.classList.add('hidden'));

window.addEventListener('click', (e) => {
    if (e.target === modal) modal.classList.add('hidden');
});
