document.addEventListener("DOMContentLoaded", function () {
    const abrirModalBtn = document.getElementById("abrirModal");
    const modal = document.getElementById("modalConfirmacion");
    const closeBtn = modal ? modal.querySelector(".close") : null;
    const cerrarModalBtn = document.getElementById("cerrarModal");

    const fechaInicioInput = document.getElementById("fechaEntrada");
    const fechaFinInput = document.getElementById("fechaSalida");
    const personasInput = document.getElementById("personas");

    if (!abrirModalBtn || !modal || !fechaInicioInput || !fechaFinInput || !personasInput) return;

    // --- Función para habilitar/deshabilitar botón ---
    function checkCampos() {
        if (fechaInicioInput.value && fechaFinInput.value && personasInput.value) {
            abrirModalBtn.disabled = false;
            abrirModalBtn.classList.remove("btn-disabled");
        } else {
            abrirModalBtn.disabled = true;
            abrirModalBtn.classList.add("btn-disabled");
        }
    }

    checkCampos();
    [fechaInicioInput, fechaFinInput, personasInput].forEach(input => {
        input.addEventListener("input", checkCampos);
    });

    // --- Abrir modal ---
    abrirModalBtn.addEventListener("click", function (e) {
        e.preventDefault();
        modal.style.display = "flex";
    });

    // --- Cerrar modal ---
    function cerrarModal() {
        modal.style.display = "none";
        window.location.href = "/"; // 🔥 redirige al index
    }

    if (closeBtn) closeBtn.addEventListener("click", cerrarModal);
    if (cerrarModalBtn) cerrarModalBtn.addEventListener("click", cerrarModal);

    window.addEventListener("click", e => { if (e.target === modal) cerrarModal(); });
});
