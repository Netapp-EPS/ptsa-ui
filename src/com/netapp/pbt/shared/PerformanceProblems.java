package com.netapp.pbt.shared;

import java.io.Serializable;

/**
 * Model class of Performance_Problems.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class PerformanceProblems implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Problems. */
	private Problems problems;

	/** Configuration. */
	private String configuration;

	/** Host_Configuration. */
	private String hostConfiguration;

	/**
	 * Constructor.
	 */
	public PerformanceProblems() {
	}

	/**
	 * Set the Problems.
	 * 
	 * @param problems
	 *            Problems
	 */
	public void setProblems(Problems problems) {
		this.problems = problems;
	}

	/**
	 * Get the Problems.
	 * 
	 * @return Problems
	 */
	public Problems getProblems() {
		return this.problems;
	}

	/**
	 * Set the Configuration.
	 * 
	 * @param configuration
	 *            Configuration
	 */
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}

	/**
	 * Get the Configuration.
	 * 
	 * @return Configuration
	 */
	public String getConfiguration() {
		return this.configuration;
	}

	/**
	 * Set the Host_Configuration.
	 * 
	 * @param hostConfiguration
	 *            Host_Configuration
	 */
	public void setHostConfiguration(String hostConfiguration) {
		this.hostConfiguration = hostConfiguration;
	}

	/**
	 * Get the Host_Configuration.
	 * 
	 * @return Host_Configuration
	 */
	public String getHostConfiguration() {
		return this.hostConfiguration;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((problems == null) ? 0 : problems.hashCode());
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
		PerformanceProblems other = (PerformanceProblems) obj;
		if (problems == null) {
			if (other.problems != null) {
				return false;
			}
		} else if (!problems.equals(other.problems)) {
			return false;
		}
		return true;
	}

}
