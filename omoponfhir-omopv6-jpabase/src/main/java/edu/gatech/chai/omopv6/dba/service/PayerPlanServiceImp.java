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
package edu.gatech.chai.omopv6.dba.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.gatech.chai.omopv6.jpa.dao.PayerPlanDao;
import edu.gatech.chai.omopv6.model.entity.PayerPlan;
import edu.gatech.chai.omopv6.model.entity.Person;

// TODO: Auto-generated Javadoc
/**
 * The Class CareSiteServiceImp.
 */
@Service
public class PayerPlanServiceImp extends BaseEntityServiceImp<PayerPlan, PayerPlanDao> implements PayerPlanService{
	
	/**
	 * Instantiates a new care site service imp.
	 */
	public PayerPlanServiceImp() {
		super(PayerPlan.class);
	}
	
	/* (non-Javadoc)
	 * @see edu.gatech.chai.omopv5.dba.service.PayerPlanService#searchByContractPersonId(edu.gatech.chai.omopv5.model.entity.Person)
	 */
	@Transactional(readOnly = true)
	public PayerPlan searchByContractPersonId(Person contractPerson) {
		EntityManager em = getEntityDao().getEntityManager();
		String query = "SELECT t FROM PayerPlan t WHERE contract_person_id like :value";
		List<? extends PayerPlan> results = em.createQuery(query, PayerPlan.class)
				.setParameter("value",contractPerson.getId()).getResultList();
		if (results.size() > 0) {
			return results.get(0);
		} else
			return null;
	}

}
