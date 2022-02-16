package com.getir.readingisgood.service;

import com.getir.readingisgood.model.DatabaseSequence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class SequenceGeneratorServiceTest {

    @InjectMocks
    private SequenceGeneratorService service;

    @Mock
    private MongoOperations mongoOperations;

    @Test
    void shouldGenerateSequence() {
        String seqName = "SEQ_SOME_DOC";
        DatabaseSequence counter = new DatabaseSequence();
        counter.setSeq(3L);

        when(mongoOperations.findAndModify(any(),
                any(), any(), any(Class.class))).thenReturn(counter);

        assertEquals(3L, service.generateSequence(seqName));
    }

    @Test
    void shouldGenerateSequenceOneWhenNull() {
        String seqName = "SEQ_SOME_DOC";

        when(mongoOperations.findAndModify(any(),
                any(), any(), any(Class.class))).thenReturn(null);

        assertEquals(1L, service.generateSequence(seqName));
    }
}