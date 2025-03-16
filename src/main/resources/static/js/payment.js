

// document.getElementById("formFileSm").addEventListener("change", function(event){
//     const file = event.target.files[0];

//     if(file){
//         const reader = new FileReader();

//         reader.onload = function(e){
//             const previewDiv = document.getElementById("filePreview");

//             if (file.type.startsWith("image/")) {
//                 previewDiv.innerHTML = `<img src="${e.target.result}" width="200px" class="img-thumbnail"/>`;
//             } else {
//                 previewDiv.innerHTML = `<p>Selected File: ${file.name} (${file.size} bytes)</p>`;
//             }

//         };
//         reader.readAsDataURL(file);
//     }
// })

function uploadFilePayment() {
    const fileInput = document.getElementById("formFileSm");

    const file = fileInput.files[0];

    if (!file) {
        alert("Please select a file!");
        return;
    }

    const formData = new FormData();

    formData.append("file", file);

    fetch("/apiFile/excel", {
        method: "POST",
        body: formData
    }).then(Response => Response.text())
        .then(data => {
            //alert(data);
        })
        .catch(error => {
            console.log("Error : ", error);
        });

    window.location.reload();



}