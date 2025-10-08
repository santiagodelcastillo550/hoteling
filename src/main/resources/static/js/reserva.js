document.addEventListener("DOMContentLoaded", function () {
    const modal = document.getElementById("modalConfirmacion");
    const modalTitulo = document.getElementById("modalTitulo");
    const modalMensaje = document.getElementById("modalMensaje");
    const modalContent = document.querySelector(".modal-content");
    const cerrarModalBtn = document.getElementById("cerrarModal");
    const closeBtn = modal ? modal.querySelector(".close") : null;

    // Si la modal ya tiene contenido dinámico desde el backend, la mostramos
    if (modalMensaje && modalMensaje.textContent.trim() !== "Mensaje aquí") {
        modal.style.display = "flex";
    }

    // Cerrar modal
    function cerrarModal() {
        modal.style.display = "none";
        if (modalContent.style.border.includes("green")) {
            // Si era una reserva confirmada → redirige al inicio
            window.location.href = "/";
        }
    }

    if (cerrarModalBtn) cerrarModalBtn.addEventListener("click", cerrarModal);
    if (closeBtn) closeBtn.addEventListener("click", cerrarModal);
    window.addEventListener("click", e => { if (e.target === modal) cerrarModal(); });
});
