$(document).ready(function()
{
    $(".item").click(function()
    {
        var quantity_inp = window.prompt("Quantididade", "");

        if(quantity_inp == "")
        {
            addItem($(this).attr("id"), 1, $(this).children().attr("value"));
        }
        else if(!(quantity_inp == null))
        {
            addItem($(this).attr("id"), quantity_inp, $(this).children().attr("value"));
        }
    });

  });

function addItem(item_id, quantity, price)
{   

    if(($("#basket").find("#item-quant-"+item_id)).length == 0)
    {
        var div = $("<div></div>");

        var item_name = $("<input>");
        item_name.attr("type", "text")
        item_name.prop("readonly");
        item_name.attr("name", "item-name-"+item_id);
        item_name.attr("value", $("#" + item_id).children().text());

        var item_quantity = $("<input>");
        item_quantity.attr("type", "text")
        item_quantity.prop("readonly");
        item_quantity.attr("name", "item-quant-"+item_id);
        item_quantity.attr("id", "item-quant-"+item_id);
        item_quantity.attr("value", quantity);
        item_quantity.hide();

        var item_price = $("<input>");
        item_price.attr("type", "text")
        item_price.prop("readonly");
        item_price.attr("name", "item-price-"+item_id);
        item_price.attr("value", price);
        item_price.hide();

        //div.append(item);
    
        $("#basket").prepend(item_name, item_quantity, item_price);
    }
    else
    {
        var item_quantity = $("#basket").find("#item-quant-"+item_id);
        item_quantity.attr("value",  (parseInt(item_quantity.attr("value")) +  parseInt(quantity)));
    }

    return 0;
}