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
 *******************************************************************************/
package edu.gatech.chai.omoponfhir.omopv6.r4.mapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Coverage;
import org.hl7.fhir.r4.model.IdType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Address.AddressUse;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import ca.uhn.fhir.rest.param.StringParam;
import ca.uhn.fhir.rest.param.TokenParam;
import edu.gatech.chai.omoponfhir.omopv6.r4.provider.CoverageResourceProvider;
import edu.gatech.chai.omoponfhir.omopv6.r4.provider.OrganizationResourceProvider;
import edu.gatech.chai.omoponfhir.omopv6.r4.utilities.AddressUtil;
import edu.gatech.chai.omopv6.dba.service.CareSiteService;
import edu.gatech.chai.omopv6.dba.service.LocationService;
import edu.gatech.chai.omopv6.dba.service.ParameterWrapper;
import edu.gatech.chai.omopv6.dba.service.PayerPlanService;
import edu.gatech.chai.omopv6.dba.service.VocabularyService;
import edu.gatech.chai.omopv6.model.entity.PayerPlan;
import edu.gatech.chai.omopv6.model.entity.CareSite;
import edu.gatech.chai.omopv6.model.entity.Concept;
import edu.gatech.chai.omopv6.model.entity.Location;

public class OmopCoverage extends BaseOmopResource<Coverage, PayerPlan, PayerPlanService> implements IResourceMapping<Coverage, PayerPlan> {
	
	private static OmopCoverage omopCoverage = new OmopCoverage();
	private LocationService locationService;
	private VocabularyService vocabularyService;

	public OmopCoverage(WebApplicationContext context) {
		super(context, PayerPlan.class, PayerPlanService.class, CoverageResourceProvider.getType());
		
	}

	public OmopCoverage() {
		super(ContextLoaderListener.getCurrentWebApplicationContext(), PayerPlan.class, PayerPlanService.class, CoverageResourceProvider.getType());
		initialize(ContextLoaderListener.getCurrentWebApplicationContext());
	}

	private void initialize(WebApplicationContext context) {		
		// Get bean for other service(s) for mapping.
		locationService = context.getBean(LocationService.class);
		vocabularyService = context.getBean(VocabularyService.class);
		
		// Get count and put it in the counts.
		getSize();

	}
	
	public static OmopCoverage getInstance() {
		return omopCoverage;
	}
	
	@Override
	public Coverage constructFHIR(Long fhirId, PayerPlan payerPlan) {
		Coverage coverage = new Coverage();

		coverage.setId(new IdType(fhirId));

		// TODO
		// General method of writing these: if omop table is not null (or Strings are not equal to "") then resource.setValue(omop table value)
		// stop_reason_source_value
		if (payerPlan.getStopReasonSourceValue() != null && payerPlan.getStopReasonSourceValue() != "") {
			
		}
		
		// sponsor_source_value
		if (payerPlan.getSponsorSourceValue() != null && payerPlan.getSponsorSourceValue() != "") {
			
		}
		
		// contract_person_id
		
		// person_id
		
		// contract_source_value
		
		// payer_plan_period_start_date
		
		// payer_plan_period_end_date
		
		// payer_source_value
		
		// plan_source_value
		
		//if (careSite.getCareSiteName() != null && careSite.getCareSiteName() != "") {
			//organization.setName(careSite.getCareSiteName());
		//}

		//if (careSite.getPlaceOfServiceConcept() != null) {
			//String codeString = careSite.getPlaceOfServiceConcept().getConceptCode();
//			String systemUriString = careSite.getPlaceOfServiceConcept().getVocabulary().getVocabularyReference();
			//String systemUriString = vocabularyService.findById(careSite.getPlaceOfServiceConcept().getVocabularyId()).getVocabularyReference();
			//String displayString = careSite.getPlaceOfServiceConcept().getConceptName();

			//CodeableConcept typeCodeableConcept = new CodeableConcept()
					//.addCoding(new Coding(systemUriString, codeString, displayString));
			//organization.addType(typeCodeableConcept);
		//}

		//if (careSite.getLocation() != null) {
			// WARNING check if mapping for lines are correct
			//organization.addAddress().setUse(AddressUse.HOME).addLine(careSite.getLocation().getAddress1())
					//.addLine(careSite.getLocation().getAddress2())
					//.setCity(careSite.getLocation().getCity()).setPostalCode(careSite.getLocation().getZip())
					//.setState(careSite.getLocation().getState());
			// .setPeriod(period);
		//}

		return coverage;
	}

	@Override
	public Long toDbase(Coverage coverage, IdType fhirId) throws FHIRException {
		// If fhirId is null, then it's CREATE.
		// If fhirId is not null, then it's UPDATE.

		Long omopId = null;
		if (fhirId != null) {
			omopId = IdMapping.getOMOPfromFHIR(fhirId.getIdPartAsLong(), CoverageResourceProvider.getType());
		} else {
			// See if we have this already. If so, we throw error.
			// Get the identifier to store the source information.
			// If we found a matching one, replace this with the careSite.
			List<Identifier> identifiers = coverage.getIdentifier();
			PayerPlan existingPayerPlan = null;
			String payerPlanSourceValue = null;
			for (Identifier identifier: identifiers) {
				if (identifier.getValue().isEmpty() == false) {
					payerPlanSourceValue = identifier.getValue();
					
					existingPayerPlan = getMyOmopService().searchByColumnString("payerPlanSourceValue", payerPlanSourceValue).get(0);
					if (existingPayerPlan != null) {
						omopId = existingPayerPlan.getId();
						break;
					}
				}
			}
		}

		PayerPlan payerPlan = constructOmop(omopId, coverage);
		
		Long omopRecordId = null;
		if (payerPlan.getId() != null) {
			omopRecordId = getMyOmopService().update(payerPlan).getId();	
		} else {
			omopRecordId = getMyOmopService().create(payerPlan).getId();
		}
		
		Long fhirRecordId = IdMapping.getFHIRfromOMOP(omopRecordId, CoverageResourceProvider.getType());
		return fhirRecordId;
	}

//	@Override
//	public Long getSize() {
//		return myOmopService.getSize(CareSite.class);
//	}
//	
//	public Long getSize(Map<String, List<ParameterWrapper>> map) {
//		return myOmopService.getSize(CareSite.class, map);
//	}
	

	@Override
	public Coverage constructResource(Long fhirId, PayerPlan entity, List<String> includes) {
		Coverage myCoverage = constructFHIR(fhirId, entity);
		/*
		if (!includes.isEmpty()) {
			if (includes.contains("Organization:partof")) {
				Reference partOfOrganization = myOrganization.getPartOf();
				if (partOfOrganization != null && partOfOrganization.isEmpty() == false) {
					IIdType partOfOrgId = partOfOrganization.getReferenceElement();
					Long partOfOrgFhirId = partOfOrgId.getIdPartAsLong();
					Long omopId = IdMapping.getOMOPfromFHIR(partOfOrgFhirId, OrganizationResourceProvider.getType());
					CareSite partOfCareSite = getMyOmopService().findById(omopId);
					Organization partOfOrgResource = constructFHIR(partOfOrgFhirId, partOfCareSite);
					
					partOfOrganization.setResource(partOfOrgResource);
				}
			}
		}
		*/
		return myCoverage;
	}

	public List<ParameterWrapper> mapParameter(String parameter, Object value, boolean or) {
		List<ParameterWrapper> mapList = new ArrayList<ParameterWrapper>();
		ParameterWrapper paramWrapper = new ParameterWrapper();
        if (or) paramWrapper.setUpperRelationship("or");
        else paramWrapper.setUpperRelationship("and");

		switch (parameter) {
		case Organization.SP_RES_ID:
			String orgnizationId = ((TokenParam) value).getValue();
			paramWrapper.setParameterType("Long");
			paramWrapper.setParameters(Arrays.asList("id"));
			paramWrapper.setOperators(Arrays.asList("="));
			paramWrapper.setValues(Arrays.asList(orgnizationId));
			paramWrapper.setRelationship("or");
			mapList.add(paramWrapper);
			break;
		case Organization.SP_NAME:
			// This is family name, which is string. use like.
			String familyString = ((StringParam) value).getValue();
			paramWrapper.setParameterType("String");
			paramWrapper.setParameters(Arrays.asList("careSiteName"));
			paramWrapper.setOperators(Arrays.asList("like"));
			paramWrapper.setValues(Arrays.asList(familyString));
			paramWrapper.setRelationship("or");
			mapList.add(paramWrapper);
			break;
		default:
			mapList = null;
		}

		return mapList;
	}

	@Override
	public PayerPlan constructOmop(Long omopId, Coverage myCoverage) {
		String careSiteSourceValue = null;
		Location location = null;
		
		CareSite careSite = null;
		if (omopId != null) {
			careSite  = getMyOmopService().findById(omopId);
			if (careSite == null) {
				try {
					throw new FHIRException(myOrganization.getId() + " does not exist");
				} catch (FHIRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			location = careSite.getLocation();
		} else {
			careSite = new CareSite();
		}
		
		Identifier identifier = myOrganization.getIdentifierFirstRep();
		if (!identifier.getValue().isEmpty()) {
			careSiteSourceValue = identifier.getValue();
			careSite.setCareSiteSourceValue(careSiteSourceValue);
		}
		
		Location existingLocation = AddressUtil.searchAndUpdate(locationService, myOrganization.getAddressFirstRep(), location);
		if (existingLocation != null) {
			careSite.setLocation(existingLocation);
		}

		// Organization.name to CareSiteName
		careSite.setCareSiteName(myOrganization.getName());

		// Organzation.type to Place of Service Concept
		List<CodeableConcept> orgTypes = myOrganization.getType();
		for (CodeableConcept orgType: orgTypes) {
			List<Coding> typeCodings = orgType.getCoding();
			if (typeCodings.size() > 0) {
				String typeCode = typeCodings.get(0).getCode();
				Long placeOfServiceId;
				try {
					placeOfServiceId = OmopConceptMapping.omopForOrganizationTypeCode(typeCode);
					Concept placeOfServiceConcept = new Concept();
					placeOfServiceConcept.setId(placeOfServiceId);
					careSite.setPlaceOfServiceConcept(placeOfServiceConcept);
				} catch (FHIRException e) {
					e.printStackTrace();
				}
			}
		}

		// Address to Location ID
		List<Address> addresses = myOrganization.getAddress();
		for (Address address: addresses) {
			// We can only store one address.
			Location retLocation = AddressUtil.searchAndUpdate(locationService, address, careSite.getLocation());
			if (retLocation != null) {
				careSite.setLocation(retLocation);
				break;
			}
		}

		return careSite;
	}
}
