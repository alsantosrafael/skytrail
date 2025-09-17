CREATE TABLE command (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    type VARCHAR(50) NOT NULL,
    protocol VARCHAR(1000) NOT NULL,
    created_at TIMESTAMP DEFAULT now()
);
