$(document).ready(function()
{
    $(".item").click(function()
    {
        var quantity_inp = window.prompt("Quantididade", "");

        if(quantity_inp == "")
        {
            addItem(
            $(this).attr("id"),
            1,
            parseFloat($(this).children(".price").text()));
        }
        else if(!(quantity_inp == null))
        {
            addItem(
            parseInt($(this).attr("id")),
            parseInt(quantity_inp),
            parseFloat($(this).children(".price").text()));
        }
    });

  });

function addItem(itemid, quantity, price)
{
    if(($("#basket").find("#sale-item-"+itemid)).length == 0)
    {
        var div = $("<div></div>");
        div.attr("id", "sale-item-"+item_id);

        var item_name = $("<b></b>");
        item_name.text($("#"+itemid).children(".name").text());

        var item_id = $("<input>");
        item_id.attr("type", "text");
        item_id.attr("name", "sale-item");
        item_id.prop("readonly", true);
        item_id.attr("value", itemid);
        item_id.hide();

        var item_quant = $("<input>");
        item_quant.attr("type", "text");
        item_quant.attr("name", "sale-item");
        item_quant.attr("class", "quant");
        item_quant.prop("readonly", true);
        item_quant.attr("value", quantity);
        item_quant.hide();

        var item_price = $("<b></b>");
        item_price.attr("type", "text");
        item_price.attr("class", "price");
        item_price.attr("value", price*quantity);
        item_price.hide();

        $("#total_price").attr("value", (parseFloat($("#total_price").attr("value"))+price*quantity).toFixed(2));

        div.append(item_id, item_name, item_quant, item_price);
    
        $("#basket").prepend(div);
    }
    else
    {
        var item_price = $("#basket").find("#sale-item-"+itemid).children(".price");
        var item_quantity = $("#basket").find("#sale-item-"+itemid).children(".quant");

        item_quantity.attr("value", (parseInt(item_quantity.attr("value"))+quantity));
        item_quantity = $("#basket").find("#sale-item-"+itemid).children(".quant");
        item_price.attr("value", (parseInt(item_quantity.attr("value"))*price));

        $("#total_price").attr("value", (parseFloat($("#total_price").attr("value"))+price*quantity).toFixed(2));
    }

    return 0;
}