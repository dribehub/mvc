const goTo = link => window.location.href = link;
const goToIndex = () => goTo("/");
const goToCustomers = () => goTo("/customers");
const goToOrders = () => goTo("/orders");
const goToItems = () => goTo("/items");

function editCtg(td) {
    const rowIndex = td.parentElement.className;
    const rowCells = document.getElementsByClassName(rowIndex)[0].children;
    const input = rowCells[0].children[0];
    const button = rowCells[1];
    input.removeAttribute("readonly");
    button.setAttribute("onclick", "saveCtg(this)");
    button.innerHTML = "Save";
    showSubmitBtn();
}
function saveCtg(td) {
    const rowIndex = td.parentElement.className;
    const rowCells = document.getElementsByClassName(rowIndex)[0].children;
    const input = rowCells[0].children[0];
    const button = rowCells[1];
    input.setAttribute("readonly", "readonly");
    button.setAttribute("onclick", "editCtg(this)");
    button.innerHTML = "Edit";
    showSubmitBtn();
}
function deleteCtg(td) {
    const table = document.getElementById("table");
    table.deleteRow(td.parentElement.className);
    for (const row of table.rows)
        row.className = row.rowIndex;
    showSubmitBtn();
}
function showSubmitBtn() {
    const submit = document.getElementById("submit");
    if (submit.hasAttribute("hidden"))
        submit.removeAttribute("hidden");
}