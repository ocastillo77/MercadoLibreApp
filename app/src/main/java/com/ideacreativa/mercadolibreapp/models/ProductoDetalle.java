package com.ideacreativa.mercadolibreapp.models;

import java.util.List;

public class ProductoDetalle {
    public String title;
    public String price;
    public String available_quantity;
    public String sold_quantity;
    public List<picture> pictures;

    public static class picture {
        public String url;
    }
}
