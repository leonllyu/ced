package org.cedj.geekseek.web.rest.relation.test.model;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import org.cedj.geekseek.domain.model.Identifiable;
import org.cedj.geekseek.domain.relation.RelationRepository;
import org.cedj.geekseek.domain.relation.model.Relation;

@ApplicationScoped
public class TestRelationRepository implements RelationRepository {

    private List<ExtendedRelation> relations = new ArrayList<ExtendedRelation>();

    @Override
    public Relation add(Identifiable source, String type, Identifiable target) {
        ExtendedRelation rel = new ExtendedRelation(source, type, target);
        relations.add(rel);
        return rel;
    }

    @Override
    public void remove(Identifiable source, String type, Identifiable target) {
        for(int i = 0; i < relations.size(); i++) {
            ExtendedRelation rel = relations.get(i);
            if(rel.getSourceId().equals(source.getId())
                && rel.getTargetId().equals(target.getId())
                && rel.getType().equals(type)) {
                relations.remove(i);
                i--;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Identifiable> List<T> findTargets(Identifiable source, String type, Class<T> targetType) {
        List<T> result = new ArrayList<T>();

        for(ExtendedRelation rel : relations) {
            if(rel.getSourceId().equals(source.getId()) && rel.getType().equals(type)) {
                result.add((T)rel.getTarget());
            }
        }
        return result;
    }

    public static class ExtendedRelation extends Relation {

        private Identifiable source;
        private Identifiable target;

        public ExtendedRelation(Identifiable source, String type, Identifiable target) {
            super(source.getId(), target.getId(), type);
            this.source = source;
            this.target = target;
        }

        public Identifiable getTarget() {
            return target;
        }

        public Identifiable getSource() {
            return source;
        }
    }
}
