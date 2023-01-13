select product_name from customers c
join orders o on o.customer_id=c.id
where lower(name)=:name