package com.netapp.pbt.shared;



public class Product  {


	private Long id;

	private String name;

	private String dl;


	/**
	 * Set the PRODUCT_ID.
	 * 
	 * @param id
	 *            PRODUCT_ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Get the PRODUCT_ID.
	 * 
	 * @return PRODUCT_ID
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Set the Product_Name.
	 * 
	 * @param name
	 *            Product_Name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the Product_Name.
	 * 
	 * @return Product_Name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the group_email.
	 * 
	 * @param dl
	 *            group_email
	 */
	public void setDl(String dl) {
		this.dl = dl;
	}

	/**
	 * Get the group_email.
	 * 
	 * @return group_email
	 */
	public String getDl() {
		return this.dl;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
