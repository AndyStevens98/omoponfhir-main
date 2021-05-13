/*******************************************************************************
 * Copyright (c) 2021 Georgia Tech Research Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *******************************************************************************/
package edu.gatech.chai.omopv6.model.entity;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="payer_plan_period")
//@NamedQueries(value={
//		@NamedQuery(name="loadCareSiteReference", query="select c.id from CareSite c WHERE c.organization.id = :serviceProvider AND c.location.id = :location")
//})
public class PayerPlan extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="payerplanperiod_seq_gen")
	@SequenceGenerator(name="payerplanperiod_seq_gen", sequenceName="payerplanperiod_id_seq", allocationSize=1)
	@Column(name="payer_plan_period_id")
	@Access(AccessType.PROPERTY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="person_id")
	private Person personId;
	
	@ManyToOne
	@JoinColumn(name="contract_person_id")
	private Person contractPersonId;
	
	@Column(name="payer_plan_period_start_date")
	private Date periodStartDate;
	
	@Column(name="payer_plan_period_end_date")
	private Date periodEndDate;
	
	@Column(name="payer_concept_id")
	private Concept payerConcept;
	
	@Column(name="payer_source_value")
	private String payerSourceValue;
	
	@Column(name="payer_source_concept_id")
	private Concept payerSourceConcept;
	
	@Column(name="plan_concept_id")
	private Concept planConcept;
	
	@Column(name="plan_source_value")
	private String planSourceValue;
	
	@Column(name="plan_source_concept_id")
	private Concept planSourceConcept;
	
	@Column(name="contract_concept_id")
	private Concept contractConcept;
	
	@Column(name="contract_source_value")
	private String contractSourceValue;
	
	@Column(name="contract_source_concept_id")
	private Concept contractSourceConcept;
	
	@Column(name="sponsor_concept_id")
	private Concept sponsorConcept;
	
	@Column(name="sponsor_source_value")
	private String sponsorSourceValue;
	
	@Column(name="sponsor_source_concept_id")
	private Concept sponsorSourceConcept;
	
	@Column(name="family_source_value")
	private String familySourceValue;
	
	@Column(name="stop_reason_concept_id")
	private Concept stopReasonConcept;
	
	@Column(name="stop_reason_source_value")
	private String stopReasonSourceValue;
	
	@Column(name="stop_reason_source_concept_id")
	private Concept stopReasonSourceConcept;
	
	public PayerPlan() {
		super();
	}
	
	public PayerPlan(Long id, Person personId, Person contractPersonId, Date periodStartDate, Date periodEndDate, Concept payerConcept, String payerSourceValue,
					Concept payerSourceConcept, Concept planConcept, String planSourceValue, Concept planSourceConcept, Concept contractConcept,
					String contractSourceValue, Concept contractSourceConcept, Concept sponsorConcept, Concept sponsorSourceConcept, String familySourceValue,
					Concept stopReasonConcept, String stopReasonSourceValue, Concept stopReasonSourceConcept) {
		super();
		
		this.id = id;
		this.personId = personId;
		this.contractPersonId = contractPersonId;
		this.periodStartDate = periodStartDate;
		this.periodEndDate = periodEndDate;
		this.payerConcept = payerConcept;
		this.payerSourceValue = payerSourceValue;
		this.payerSourceConcept = payerSourceConcept;
		this.planConcept = planConcept;
		this.planSourceValue = planSourceValue;
		this.planSourceConcept = planSourceConcept;
		this.contractConcept = contractConcept;
		this.contractSourceValue = contractSourceValue;
		this.contractSourceConcept = contractSourceConcept;
		this.sponsorConcept = sponsorConcept;
		this.sponsorSourceConcept = sponsorSourceConcept;
		this.familySourceValue = familySourceValue;
		this.stopReasonConcept = stopReasonConcept;
		this.stopReasonSourceValue = stopReasonSourceValue;
		this.stopReasonSourceConcept = stopReasonSourceConcept;
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Person getPersonId() {
		return personId;
	}
	
	public void setPersonId(Person personId) {
		this.personId = personId;
	}
	
	public Person getContractPersonId() {
		return contractPersonId;
	}
	
	public void setContractPersonId(Person contractPersonId) {
		this.contractPersonId = contractPersonId;
	}
	
	public Date getPeriodStartDate() {
		return periodStartDate;
	}
	
	public void setPeriodStartDate(Date periodStartDate) {
		this.periodStartDate = periodStartDate;
	}
	
	public Date getPeriodEndDate() {
		return periodEndDate;
	}
	
	public void setPeriodEndDate(Date periodEndDate) {
		this.periodEndDate = periodEndDate;
	}
	
	public Concept getPayerConcept() {
		return payerConcept;
	}
	
	public void setPayerConcept(Concept payerConcept) {
		this.payerConcept = payerConcept;
	}
	
	public String getPayerSourceValue() {
		return payerSourceValue;
	}
	
	public void setPayerSourceValue(String payerSourceValue) {
		this.payerSourceValue = payerSourceValue;
	}
	
	public Concept getPayerSourceConcept() {
		return payerSourceConcept;
	}
	
	public void setPayerSourceConcept(Concept payerSourceConcept) {
		this.payerSourceConcept = payerSourceConcept;
	}
	
	public Concept getPlanConcept() {
		return planConcept;
	}
	
	public void setPlanConcept(Concept planConcept) {
		this.planConcept = planConcept;
	}
	
	public String getPlanSourceValue() {
		return planSourceValue;
	}
	
	public void setPlanSourceValue(String planSourceValue) {
		this.planSourceValue = planSourceValue;
	}
	
	public Concept getPlanSourceConcept() {
		return planSourceConcept;
	}
	
	public void setPlanSourceConcept(Concept planSourceConcept) {
		this.planSourceConcept = planSourceConcept;
	}

	public Concept getContractConcept() {
		return contractConcept;
	}
	
	public void setContractConcept(Concept contractConcept) {
		this.contractConcept = contractConcept;
	}
	
	public String getContractSourceValue() {
		return contractSourceValue;
	}
	
	public void setContractSourceValue(String contractSourceValue) {
		this.contractSourceValue = contractSourceValue;
	}
	
	public Concept getContractSourceConcept() {
		return contractSourceConcept;
	}
	
	public void setContractSourceConcept(Concept contractSourceConcept) {
		this.contractSourceConcept = contractSourceConcept;
	}
	
	public Concept getSponsorConcept() {
		return sponsorConcept;
	}
	
	public void setSponsorConcept(Concept sponsorConcept) {
		this.sponsorConcept = sponsorConcept;
	}
	
	public String getSponsorSourceValue() {
		return sponsorSourceValue;
	}
	
	public void setSponsorSourceValue(String sponsorSourceValue) {
		this.sponsorSourceValue =  sponsorSourceValue;
	}
	
	public Concept getSponsorSourceConcept() {
		return sponsorSourceConcept;
	}
	
	public void setSponsorSourceConcept(Concept sponsorSourceConcept) {
		this.sponsorSourceConcept = sponsorSourceConcept;
	}
	
	public String getFamilySourceValue() {
		return familySourceValue;
	}
	
	public void setFamilySourceValue(String familySourceValue) {
		this.familySourceValue = familySourceValue;
	}
	
	public Concept getStopReasonConcept() {
		return stopReasonConcept;
	}
	
	public void setStopReasonConcept(Concept stopReasonConcept) {
		this.stopReasonConcept = stopReasonConcept;
	}
	
	public String getStopReasonSourceValue() {
		return stopReasonSourceValue;
	}
	
	public void setStopReasonSourceValue(String stopReasonSourceValue) {
		this.stopReasonSourceValue = stopReasonSourceValue;
	}
	
	public Concept getStopReasonSourceConcept() {
		return stopReasonSourceConcept;
	}
	
	public void setStopReasonSourceConcept(Concept stopReasonSourceConcept) {
		this.stopReasonSourceConcept = stopReasonSourceConcept;
	}
	
	@Override
	public Long getIdAsLong() {
		return getId();
	}
	
}
