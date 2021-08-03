function setColor(element, color) {
    color = "color: " + color + ";"
    element.setAttribute("style", color);
}

function getCtg(btn) {
    const rowIndex = btn.parentElement.className;
    return document.getElementsByClassName(rowIndex)[0]
        .children[0].children[0];
}

function showSubmitBtn() {
    const submit = document.getElementById("submitAll");
    if (submit.hasAttribute("hidden"))
        submit.removeAttribute("hidden");
}

function editCtg(btn) {
    const ctg = getCtg(btn);
    ctg.removeAttribute("readonly");
    btn.setAttribute("onclick", "saveCtg(this)");
    btn.setAttribute("onmouseenter", "setColor(this, 'green')");
    setColor(btn, "green");
    btn.innerHTML = "Save";
}

function saveCtg(btn) {
    const ctg = getCtg(btn);
    ctg.setAttribute("readonly", "readonly");
    btn.setAttribute("onclick", "editCtg(this)");
    btn.setAttribute("onmouseenter", "setColor(this, 'darkorange')");
    setColor(btn, "darkorange");
    btn.innerHTML = "Edit";
    showSubmitBtn();
}

function deleteCtg(btn) {
    const table = document.getElementById("table");
    const rowIndex = btn.parentElement.className;
    table.rows[rowIndex].setAttribute("hidden", "hidden");
    const isDeleted = document.getElementsByClassName(rowIndex)[0].children[3];
    isDeleted.setAttribute("th:value", "${true}");
    showSubmitBtn();
}

function addNewCtg() {
    showSubmitBtn();
    document.getElementById("submitNew").click();
}