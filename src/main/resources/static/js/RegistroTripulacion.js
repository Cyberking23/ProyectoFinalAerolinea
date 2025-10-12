const modal = document.getElementById('tripModal');
const openBtn = document.getElementById('openTripModal');
const closeBtn = document.getElementById('closeTripModal');
const cancelBtn = document.getElementById('cancelTripModal');

if (openBtn) openBtn.addEventListener('click', () => modal.classList.remove('hidden'));
if (closeBtn) closeBtn.addEventListener('click', () => modal.classList.add('hidden'));
if (cancelBtn) cancelBtn.addEventListener('click', () => modal.classList.add('hidden'));

window.addEventListener('click', (e) => {
    if (e.target === modal) modal.classList.add('hidden');
});
