package shopping.test;

import org.junit.Before;
import org.junit.Test;

import shopping.core.Cart;
import shopping.core.Item;
import shopping.core.ItemType;
import shopping.core.Product;
import shopping.core.User;
import shopping.core.UserType;
import shopping.discount.DiscountPolicy;
import shopping.discount.ThresholdDiscount;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

/*
 * Test Cases for no discount policy
 */
/**
 * @author Asim khan
 *
 */
public class TestDiscounts {

	private Item groceryItem;
	private Item otherItem;
	private User employee;
	private User affiliate;
	private User simpleUser;
	private User simpleUserWith2Years;
	private DiscountPolicy discountPolicy;

	@Before
	public void setUp() {
		employee = new User(UserType.EMPLOYEE, "John");
		affiliate = new User(UserType.AFFILIATE, "Michael");
		simpleUser = new User(UserType.SIMPLE, "Bob");
		simpleUserWith2Years = new User(UserType.SIMPLE, "Alex", LocalDateTime.of(2014, 7, 19, 6, 40, 45));
		groceryItem = new Product("Rice", 20, ItemType.GROCERY);
		otherItem = new Product("TV", 222, ItemType.OTHER);
		discountPolicy = new ThresholdDiscount();
	}

	/**
	 * No discount because of grocery item + employee
	 */
	@Test
	public void employeeWithGroceryTest() {
		Cart cart = new Cart(employee, discountPolicy);
		cart.add(groceryItem, 4);
		assertEquals(80, cart.total(), 0.01);

	}

	/**
	 * 30% discount then 5 dollars off of each 100 dollars of total price because of
	 * other item Total 222 * 4 = 888 After 30% discount = 621.6 After 30 dollars
	 * off due to price over $600 = 591.6
	 */
	@Test
	public void employeeWithOtherItemTest() {
		Cart cart = new Cart(employee, discountPolicy);
		cart.add(otherItem, 4);
		assertEquals(591.6, cart.total(), 0.01);

	}

	/**
	 * No discount because of grocery item + affiliate
	 */
	@Test
	public void affiliateWithGroceryTest() {
		Cart cart = new Cart(affiliate, discountPolicy);
		cart.add(groceryItem, 4);
		assertEquals(80, cart.total(), 0.01);

	}

	/**
	 * 10% discount then 5 dollars off of each 100 dollars of total price because of
	 * other item Total 222 * 4 = 888 After 10% discount = 799.2 After 35 dollars
	 * off due to price over $700 = 591.6
	 */
	@Test
	public void affiliateWithOtherItemTest() {
		Cart cart = new Cart(affiliate, discountPolicy);
		cart.add(otherItem, 4);
		assertEquals(764.2, cart.total(), 0.01);

	}

	/**
	 * No discount because of grocery item + simpleUser
	 */
	@Test
	public void simpleUserWithGroceryTest() {
		Cart cart = new Cart(simpleUser, discountPolicy);
		cart.add(groceryItem, 4);
		assertEquals(80, cart.total(), 0.01);

	}

	/**
	 * Total 222 * 4 = 888 No percentage discount because user is a customer for
	 * less than 2 years After 40 dollars off due to price over $800 = 848
	 */
	@Test
	public void simpleUserWithOtherItemTest() {
		Cart cart = new Cart(simpleUser, discountPolicy);
		cart.add(otherItem, 4);
		assertEquals(848, cart.total(), 0.01);
	}

	/**
	 * No discount because of grocery item + simpleUserWith2Years
	 */
	@Test
	public void simpleUserWith2YearsWithGroceryTest() {
		Cart cart = new Cart(simpleUserWith2Years, discountPolicy);
		cart.add(groceryItem, 4);
		assertEquals(80, cart.total(), 0.01);

	}

	/**
	 * 5% discount then 5 dollars off of each 100 dollars of total price because of
	 * other item Total 222 * 4 = 888 After 5% discount = 843.6 After 40 dollars off
	 * due to price over $800 =803.6
	 */
	@Test
	public void simpleUserWith2YearsWithOtherItemTest() {
		Cart cart = new Cart(simpleUserWith2Years, discountPolicy);
		cart.add(otherItem, 4);
		assertEquals(803.6, cart.total(), 0.01);

	}

	/**
	 * Total (20 * 4) + (222 * 4) = 968 No discount on grocery items = 968 still
	 * After 30% discount on 4 other items totaling 888 = 701.6 After 35 dollars
	 * off due to price over $700 =666.6
	 */
	@Test
	public void employeeWithGroceryAndOtherItemTest() {
		Cart cart = new Cart(employee, discountPolicy);
		cart.add(groceryItem, 4);
		cart.add(otherItem, 4);
		assertEquals(666.6, cart.total(), 0.01);

	}
}
