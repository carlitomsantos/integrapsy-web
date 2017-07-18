function triggerBlockUI(data) {
    if (data.status === "begin") {
        PF("widgetBlockUI").show();
    }
    else if (data.status === "success") {
        PF("widgetBlockUI").hide();
    }
}