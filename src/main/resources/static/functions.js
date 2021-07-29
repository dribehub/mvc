const goTo = link => window.location.href = link;
const goToIndex = () => goTo("/");
const goToCustomers = () => goTo("/customers");
const goToOrders = () => goTo("/orders");
const goToItems = () => goTo("/items");