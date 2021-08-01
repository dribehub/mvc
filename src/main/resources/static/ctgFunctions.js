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
    const submit = document.getElementById("submit");
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
    showSubmitBtn();
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
    table.deleteRow(btn.parentElement.className);
    for (const row of table.rows)
        row.className = row.rowIndex;
    showSubmitBtn();
}

function addNewCtg() {
    const newCtg = document.getElementById("newCtg");
    const inputVal = newCtg.value;
    newCtg.value = "";
    const table = document.getElementById("table");
    const newRowIndex = table.rows.length;
    const newRow = table.insertRow(newRowIndex);
    newRow.className = newRowIndex;
    addNewCtgInputField(newRow, inputVal);
    addNewCtgEditBtn(newRow);
    addNewCtgDeleteBtn(newRow);
}

function addNewCtgInputField(newRow, value) {
    const field = document.createElement("input");
    field.type = "text";
    field.className = "input";
    field.setAttribute("value", value);
    field.setAttribute("th:field", "*{categories[__${stats.index}__].name}");
    field.setAttribute("readonly", "readonly");
    newRow.insertCell().insertAdjacentHTML("afterbegin", field.outerHTML);
    showSubmitBtn();
}

function addNewCtgEditBtn(newRow) {
    const editBtn = newRow.insertCell();
    editBtn.innerHTML = "Edit";
    editBtn.className = "btn";
    editBtn.setAttribute("onclick", "editCtg(this)");
    editBtn.setAttribute("onmouseenter", "setColor(this, 'darkorange')");
    editBtn.setAttribute("onmouseleave", "setColor(this, 'black')");
}

function addNewCtgDeleteBtn(newRow) {
    const deleteBtn = newRow.insertCell();
    deleteBtn.innerHTML = "Delete";
    deleteBtn.className = "btn";
    deleteBtn.setAttribute("onclick", "deleteCtg(this)");
    deleteBtn.setAttribute("onmouseenter", "setColor(this, 'red')");
    deleteBtn.setAttribute("onmouseleave", "setColor(this, 'black')");
}
