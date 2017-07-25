package io.robusta;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;


@RequestScoped
public class ForaProducer {

	@Produces
	@ForaQualifier
	public ForaDataSource getBean(){
		return ForaDataSource.getInstance();
	}
}
