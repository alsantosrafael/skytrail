CREATE TABLE task_run (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    status VARCHAR(255) DEFAULT 'WAITING',
    task_id UUID NOT NULL,
    dag_run_id UUID NOT NULL,
    started_at TIMESTAMP DEFAULT null,
    finished_at TIMESTAMP DEFAULT null,
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now(),

    FOREIGN KEY (task_id) references task(id),
    FOREIGN KEY (dag_run_id) references dag_run(id)
)