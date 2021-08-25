const goTo = link => window.location.href = link;
const goToIndex = () => goTo("/");
const goToCustomers = () => goTo("/customers");
const goToOrders = () => goTo("/orders");
const goToItems = () => goTo("/items");
const paintIcon = (el, icon, color) => el.src = getIcon(icon, color);
const getIcon = (icon, color) => `https://img.icons8.com/ios-glyphs/30/${color}/${icon}.png`;
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

const black = '000000', red = 'df4740', green = '40a21d', teal = '1a81a4',
      cancel = 'cancel', approve = 'checked--v1', trash = 'filled-trash',
      message = 'no-comments', details = 'view-delivery',
      edit = 'edit--v1', ctg = 'category', price = 'price-tag-euro';
const cancelOn = el => paintIcon(el, cancel, red);
const cancelOff = el => paintIcon(el, cancel, black);
const approveOn = el => paintIcon(el, approve, green);
const approveOff = el => paintIcon(el, approve, black);
const trashOn = el => paintIcon(el, trash, red);
const trashOff = el => paintIcon(el, trash, black);
const messageOn = el => paintIcon(el, message, teal);
const messageOff = el => paintIcon(el, message, black);
const detailsOn = el => paintIcon(el, details, teal);
const detailsOff = el => paintIcon(el, details, black);
const editOn = el => paintIcon(el, edit, teal);
const editOff = el => paintIcon(el, edit, black);
const ctgOn = el => paintIcon(el, ctg, teal);
const ctgOff = el => paintIcon(el, ctg, black);
const priceOn = el => paintIcon(el, price, teal);
const priceOff = el => paintIcon(el, price, black);