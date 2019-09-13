package shopping.test;

import org.junit.Before;
import org.junit.Test;

import shopping.core.Cart;
import shopping.core.Item;
import shopping.core.ItemType;
import shopping.core.Product;
import shopping.core.User;
import shopping.core.UserType;

import static org.junit.Assert.assertEquals;

/*
 * Test Cases for no discount policy for any type and just to see if cart is working
 */
/**
 * @author Asim khan
 *
 */
public class TestWithoutDiscounts {

	private Cart cart;
	private Item item;

	@Before
	public void setUp() {
		User user = new User(UserType.SIMPLE, "Bob");
		cart = new Cart(user);
		item = new Product("something", 1000, ItemType.OTHER);
	}

	@Test
	public void emptyCartCostsZeroTest() {
		assertEquals(0, cart.total(), 0.01);
	}

	@Test
	public void singleBasicItemCostsItsUnitPriceTest() {
		cart.add(item);
		assertEquals(item.getUnitPrice(), cart.total(), 0.01);
	}

	@Test
	public void multipleBasicItemsCostProportionallyTest() {
		int howMany = 3;
		cart.add(item, howMany);
		assertEquals(howMany * item.getUnitPrice(), cart.total(), 0.01);
	}

	@Test
	public void separatelyAddingTest() {
		int howMany = 3;
		for (int i = howMany; i > 0; i--) {
			cart.add(item);
		}
		assertEquals(howMany * item.getUnitPrice(), cart.total(), 0.01);
	}
}
