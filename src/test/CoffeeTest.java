package test;

import wrapper.CoffeeShopConfigAPI;
import wrapper.CreateCoffeeShop;
import wrapper.UpdateCoffeeShop;

public abstract class CoffeeTest {
    private CreateCoffeeShop createApi = new CoffeeShopConfigAPI();

    private UpdateCoffeeShop updateApi = new CoffeeShopConfigAPI();

    public CreateCoffeeShop getCreateApi() {
        return createApi;
    }

    public void setCreateApi(CreateCoffeeShop createApi) {
        this.createApi = createApi;
    }

    public UpdateCoffeeShop getUpdateApi() {
        return updateApi;
    }

    public void setUpdateApi(UpdateCoffeeShop updateApi) {
        this.updateApi = updateApi;
    }

    public abstract void executeTest();
}
