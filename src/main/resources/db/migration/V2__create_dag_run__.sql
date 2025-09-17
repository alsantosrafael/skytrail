CREATE TABLE dag_run (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    status VARCHAR(255) DEFAULT 'WAITING',
    dag_id UUID NOT NULL,
    started_at TIMESTAMP DEFAULT null,
    finished_at TIMESTAMP DEFAULT null,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now(),

    FOREIGN KEY (dag_id) references dag(id)
)