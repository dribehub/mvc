const goTo = link => window.location.href = link;
const goToIndex = () => goTo("/");
const goToCustomers = () => goTo("/customers");
const goToOrders = () => goTo("/orders");
const goToItems = () => goTo("/items");
const onEnter = (input, action) => {
    input.addEventListener("keyup", e => {
        if (e.key === 'Enter') {
            e.preventDefault();
            action();
        }
    })
}