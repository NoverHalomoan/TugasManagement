function openModaluser(button) {
    let iduser = button.getAttribute("data-bs-iduser");
    let name = button.getAttribute("data-bs-name");
    let activestatus = button.getAttribute("data-bs-activestatus");
    let statusflag = button.getAttribute("data-bs-status");
    document.getElementById("username").value = name;
    document.getElementById("userStatus").value = activestatus;
    document.getElementById("iduser").value = iduser;
    document.getElementById("flagstatus").value = statusflag;
    if (statusflag == 1) {
        document.getElementById("hedaertagform").innerHTML = "Delete Data User";
        document.getElementById("question-delete-user").innerHTML = "Apakah Anda Yakin Ingin Menghapus User " + name + " Ini ?";
        document.getElementById("name-label").hidden = true;
        document.getElementById("userStatus").hidden = true;
        document.getElementById("username").hidden = true;
        document.getElementById("status-label").hidden = true;
        document.getElementById("question-delete-user").hidden = false;

    } else {
        document.getElementById("hedaertagform").innerHTML = "Update Data User";
        document.getElementById("question-delete-user").hidden = true;
        document.getElementById("name-label").hidden = false;
        document.getElementById("userStatus").hidden = false;
        document.getElementById("username").hidden = false;
        document.getElementById("status-label").hidden = false;

    }



    let model = new bootstrap.Modal(document.getElementById("editUserModal"));
    model.show();
}

function uploadmanualuser(button) {
    let modal = new bootstrap.Modal(document.getElementById("uploaddatausers"));
    modal.show();
}