CREATE TABLE task(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    dag_id UUID NOT NULL,
    command_id UUID,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    created_at TIMESTAMP DEFAULT now(),
    updated_at TIMESTAMP DEFAULT now(),

    FOREIGN KEY (dag_id) references dag(id),
    FOREIGN KEY (command_id) references command(id)
)