const goTo = link => window.location.href = link;
const goToIndex = () => goTo("/");
const goToCustomers = () => goTo("/customers");
const goToOrders = () => goTo("/orders");
const goToItems = () => goTo("/items");
const setColor = (el, color) => el.setAttribute("style", "color: " + color + ";");

function getCtg(td) {
    const rowIndex = td.parentElement.className;
    const row = document.getElementsByClassName(rowIndex)[0];
    return row.children[0].children[0];
}
function editCtg(td) {
    const ctg = getCtg(td);
    ctg.removeAttribute("readonly");
    td.setAttribute("onclick", "saveCtg(this)");
    td.setAttribute("onmouseenter", "setColor(this, 'green')");
    setColor(td, "green");
    td.innerHTML = "Save";
    showSubmitBtn();
}
function saveCtg(td) {
    const ctg = getCtg(td);
    ctg.setAttribute("readonly", "readonly");
    td.setAttribute("onclick", "editCtg(this)");
    td.setAttribute("onmouseenter", "setColor(this, 'darkorange')");
    setColor(td, "darkorange");
    td.innerHTML = "Edit";
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