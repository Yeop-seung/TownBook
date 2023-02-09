import React, { useState, useEffect, useRef } from "react";

const ItemList = () => {
    const [page, setPage] = useState(1);
    const [loading, setLoading] = useState(false);
    const observer = useRef(null);

    useEffect(() => {
    observer.current = new IntersectionObserver(
        ([entry]) => {
        if (entry.isIntersecting) {
            setPage(prevPage => prevPage + 1);
        }
        },
        { rootMargin: "200px" }
    );
    return () => {
        observer.current.disconnect();
    };
    }, []);

    useEffect(() => {
    if (loading) return;
    setLoading(true);
    fetch(`https://jsonplaceholder.typicode.com/posts?_page=${page}`)
        .then(res => res.json())
        .then(data => {
        setTimeout(() => {
            setLoading(false);
        }, 1000);
        });
    }, [loading, page]);

    return (
    <div>
        {Array.from({ length: 10 }, (_, i) => (
        <div
            key={i}
            ref={node => {
            if (node) observer.current.observe(node);
            }}
        >
            Item {i + 10 * (page - 1)}
        </div>
        ))}
        {loading && <div>Loading...</div>}
    </div>
    );
    };

export default ItemList;