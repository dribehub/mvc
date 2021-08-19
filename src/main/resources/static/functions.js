const goTo = link => window.location.href = link;
const goToIndex = () => goTo("/");
const goToCustomers = () => goTo("/customers");
const goToOrders = () => goTo("/orders");
const goToItems = () => goTo("/items");
function onEnter(input, action) {
    input.addEventListener("keyup", e => {
        if (e.key === 'Enter') {
            e.preventDefault();
            action();
        }
    })
}
function onClick(input, action) {
    input.addEventListener("click", action);
}
function focusError(error, input) {
    $(error).removeAttr("hidden");
    $(input).focus();
    $(input).select();
}