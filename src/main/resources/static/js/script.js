function openModaluser(button) {
    let iduser = button.getAttribute("data-bs-iduser");
    let name = button.getAttribute("data-bs-name")
    let activestatus = button.getAttribute("data-bs-activestatus")
    document.getElementById("username").value = name;
    document.getElementById("userStatus").value = activestatus;
    document.getElementById("iduser").value = iduser;

    let model = new bootstrap.Modal(document.getElementById("editUserModal"));
    model.show();
}