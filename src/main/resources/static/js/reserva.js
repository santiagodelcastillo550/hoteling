document.addEventListener("DOMContentLoaded", function () {
    const abrirModalBtn = document.getElementById("abrirModal");
    const modal = document.getElementById("modalConfirmacion");
    const closeBtn = modal ? modal.querySelector(".close") : null;
    const cerrarModalBtn = document.getElementById("cerrarModal");

    const fechaInicioInput = document.getElementById("fechaEntrada");
    const fechaFinInput = document.getElementById("fechaSalida");
    const personasInput = document.getElementById("personas");

    const form = document.querySelector("form.reserva-form");

    if (!abrirModalBtn || !modal || !fechaInicioInput || !fechaFinInput || !personasInput || !form) return;

    // --- Habilitar/deshabilitar botón según campos ---
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

    // --- Confirmar reserva (botón dentro del modal) ---
    if (cerrarModalBtn) {
        cerrarModalBtn.addEventListener("click", function () {
            modal.style.display = "none";

            // 🧠 Enviar el formulario (guarda en base de datos)
            form.submit();
        });
    }

    // --- Cerrar modal con la X ---
    function cerrarModal() {
        modal.style.display = "none";
    }

    if (closeBtn) closeBtn.addEventListener("click", cerrarModal);

    // --- Cerrar modal al hacer click fuera ---
    window.addEventListener("click", e => { 
        if (e.target === modal) cerrarModal(); 
    });
});
