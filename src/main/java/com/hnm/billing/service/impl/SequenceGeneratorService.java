package com.hnm.billing.service.impl;

import com.hnm.billing.model.DatabaseSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceGeneratorService {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private MongoTemplate mongoTemplate;

    public long generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

    public long generateConnectionNumber(String seqName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(seqName));
        DatabaseSequence databaseSequence = mongoTemplate.findOne(query, DatabaseSequence.class);
        if (databaseSequence != null) {
            Update updateSequence = new Update();
            long incrementedValue = databaseSequence.getSeq() + 1l;
            updateSequence.set("seq", incrementedValue);
            mongoTemplate.updateFirst(query, updateSequence, DatabaseSequence.class);
            return incrementedValue;
        } else {
            databaseSequence = new DatabaseSequence();
            databaseSequence.setId(seqName);
            databaseSequence.setSeq(10001);
            mongoTemplate.save(databaseSequence);
            return 10001;
        }
    }
}
