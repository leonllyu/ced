package org.cedj.geekseek.domain.conference.test.integration;

import java.io.File;

import javax.inject.Inject;

import org.cedj.geekseek.domain.Repository;
import org.cedj.geekseek.domain.conference.model.Conference;
import org.cedj.geekseek.domain.conference.test.TestUtils;
import org.cedj.geekseek.domain.persistence.test.integration.PersistenceDeployments;
import org.cedj.geekseek.domain.test.integration.BaseTransactionalSpecification;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ConferenceRepositoryTransactionalTestCase extends
    BaseTransactionalSpecification<Conference, Repository<Conference>> {

    private static final String UPDATED_NAME = "TEST UPDATED";

    public ConferenceRepositoryTransactionalTestCase() {
        super(Conference.class);
    }

    // Given
    @Deployment
    public static WebArchive deploy() {
        return ShrinkWrap.create(WebArchive.class)
            .addAsLibraries(
                ConferenceDeployments.conference().addClasses(ConferenceTestCase.class, TestUtils.class)
                    .addAsManifestResource(new StringAsset(
                        PersistenceDeployments.descriptor() .exportAsString()), "persistence.xml")
                    .addAsManifestResource(new File("src/main/resources/META-INF/beans.xml")))
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
            .addClass(BaseTransactionalSpecification.class);
    }

    @Inject
    private Repository<Conference> repository;

    @Override
    protected Conference createNewDomainObject() {
        return TestUtils.createConference();
    }

    @Override
    protected Conference updateDomainObject(Conference domain) {
        return domain.setName(UPDATED_NAME);
    }

    @Override
    protected void validateUpdatedDomainObject(Conference domain) {
        Assert.assertEquals(UPDATED_NAME, domain.getName());
    }

    @Override
    protected Repository<Conference> getRepository() {
        return repository;
    }
}
