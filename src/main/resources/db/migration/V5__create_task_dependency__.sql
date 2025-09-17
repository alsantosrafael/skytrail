CREATE TABLE task_dependency (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    prior UUID NOT NULL,
    next UUID NOT NULL,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now(),

    FOREIGN KEY (prior) references task(id),
    FOREIGN KEY (next) references task(id)
)