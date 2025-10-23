(function () {
    const token = localStorage.getItem("jwt");
    const rol = (localStorage.getItem("rol") || "").toUpperCase();
    console.log(rol)

    if (!token) { window.location.href = "/login"; return; }
    if (rol !== "ADMIN") { window.location.href = "/cliente"; }
})();