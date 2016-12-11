package com.Core.vaadin.workingWithForms.jodaTime;

import java.util.Date;
import java.util.Locale;

import org.joda.time.DateTime;

import com.vaadin.data.fieldgroup.DefaultFieldGroupFieldFactory;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroupFieldFactory;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.converter.Converter;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.VerticalLayout;

public class JodaTime extends FormLayout {
	
	private ProbandoJodaTime post = new ProbandoJodaTime();
	private String label = "I really need that stuff";
	private DateTime create = DateTime.now();
	
	public JodaTime( ) {
		setSizeUndefined();
		setMargin(true);
		
		post.setText(label);
		post.setCreate(create);
		/*
		 * create a BeanItem to which we pass the instance of the post, class and put that
		 * instance into the instace of FieldGroup 
		 */
		BeanItem<ProbandoJodaTime> postBean = new BeanItem<ProbandoJodaTime>(post);
		FieldGroup fieldGroup = new FieldGroup(postBean);
		
		FieldGroupFieldFactory fieldFactory = new JodaFieldFactory();
		fieldGroup.setFieldFactory(fieldFactory);
		
		/*
		 * crear el formLayout y add, componentes usando
		 * builAndBind() de la clase fieldGroup 
		 */
	
		addComponents(fieldGroup.buildAndBind("text") , fieldGroup.buildAndBind("create"));
		
	
	}
	
	public class JodaFieldFactory extends DefaultFieldGroupFieldFactory {

		@Override
	    public <T extends Field> T createField(Class<?> type, Class<T> fieldType) {
	        T field;
	        if(type.isAssignableFrom(DateTime.class)) {
	        	DateField dateField = new DateField();
	        	dateField.setConverter(new DateTimeConverter());
	        	field = (T) dateField;
	        } else {
	        	field = super.createField(type, fieldType);
	        }
			
	        return field;
	    }

	}
	
	public class DateTimeConverter implements Converter<Date , DateTime> {

		@Override
		public DateTime convertToModel(Date value, Class<? extends DateTime> targetType, Locale locale)
				throws com.vaadin.data.util.converter.Converter.ConversionException {
			
			return new DateTime(value);
		}

		@Override
		public Date convertToPresentation(DateTime value, Class<? extends Date> targetType, Locale locale)
				throws com.vaadin.data.util.converter.Converter.ConversionException {
			Date date = value.toDate();
			
			return date;
		}

		@Override
		public Class<DateTime> getModelType() {
			
			return DateTime.class;
		}

		@Override
		public Class<Date> getPresentationType() {
			
			return Date.class;
		}
		
	}

	
}
