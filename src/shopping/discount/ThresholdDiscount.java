package shopping.discount;

/*
 * To generate discount of $5 for every 100 dollars in cart
 */
public class ThresholdDiscount implements DiscountPolicy {

	@Override
	public double applyDiscount(double totalAmount) {
		
		if (totalAmount < 100) {
			return totalAmount;
		}
		
		int discountFactor = (int) totalAmount / 100;
		double discount = discountFactor * 5d;
		return totalAmount - discount; 
	}

}
