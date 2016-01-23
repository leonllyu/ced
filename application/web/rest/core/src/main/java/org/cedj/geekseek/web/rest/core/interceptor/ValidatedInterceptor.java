package org.cedj.geekseek.web.rest.core.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.interceptor.InvocationContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import org.cedj.geekseek.web.rest.core.Representation;

public class ValidatedInterceptor implements RESTInterceptor {

    @Inject
    private Validator validator;

    @Override
    public int getPriority() {
        return 50;
    }

    public Object invoke(InvocationContext ic) throws Exception {
        if(isIncomingDataRequest(ic)) {
            validateAllRepresentations(ic);
        }
        return ic.proceed();
    }

    private boolean isIncomingDataRequest(InvocationContext ic) {
        return ic.getMethod().isAnnotationPresent(PUT.class) ||
            ic.getMethod().isAnnotationPresent(POST.class);
    }

    private void validateAllRepresentations(InvocationContext ic) {
        for(Object obj : ic.getParameters()) {
            if(obj instanceof Representation) {
                validate(obj);
            }
        }
    }

    private void validate(Object conference) {
        Set<ConstraintViolation<Object>> violations = validator.validate(conference);
        if(!violations.isEmpty()) {
            throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
        }
    }
}
