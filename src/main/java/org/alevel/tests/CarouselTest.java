package org.alevel.tests;
import org.alevel.pages.CarouselPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CarouselTest extends BaseTest {

    @Test
    public void testCarousel() {
        CarouselPage carouselPage = new CarouselPage(driver);

        carouselPage.waitForCarouselVisibility();

        // Assuming 4 dots based on your setup
        for (int i = 0; i < 4; i++) {
            carouselPage.clickCarouselDot(i);
        }

        // Assertions
        int expectedInactiveDots = 4; // Total number of dots - 1 for active
        Assert.assertEquals(carouselPage.getNumberOfActiveDots(), 1, "Неверное количество активных элементов в карусели");
        Assert.assertEquals(carouselPage.getNumberOfInactiveDots(), expectedInactiveDots, "Неверное количество неактивных элементов в карусели");
    }
}
