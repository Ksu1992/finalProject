package org.alevel.tests;
import org.alevel.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {
    @Test
    public void testHomePageElements() {
        HomePage homePage = new HomePage(driver);

        // Проверка логотипа
        Assert.assertTrue(homePage.isLogoDisplayed(), "Логотип не отображается");

        // Проверка меню навигации
        Assert.assertTrue(homePage.isNavigationMenuDisplayed(), "Меню навигации не отображается");

        // Проверка поисковой строки
        Assert.assertTrue(homePage.isSearchBoxDisplayed(), "Поиск товаров отсутствует");

        // Проверка списка популярных продуктов
        Assert.assertTrue(homePage.isPopularProductsListDisplayed(), "Список популярных товаров не отображается");

        // Проверка других элементов
        Assert.assertTrue(homePage.isHomePageDisplayed(), "Страница домашней страницы не отображается");
        Assert.assertTrue(homePage.isForUsDisplayed(), "Секция 'Для нас' не отображается");
        Assert.assertTrue(homePage.isProductCatalogDisplayed(), "Каталог продуктов не отображается");
        Assert.assertTrue(homePage.isContactsDisplayed(), "Контактная информация не отображается");
    }
}
