# Transacciones
## Qué es una transacción
Una transacción es un conjunto de acciones que deben realizarse de forma conjunta, si alguna de ellas no se completa no lo hace ninguna.

### Ejemplo
Si reservamos un libro en una biblioteca deben realizarse una serie de operaciones:
- Que el libro se encuentre en la biblioteca
- Que el libro esté disponible
- Que el usuario esté registrado en el sistema
- Retirar el libro
- Entregar el libro

En términos de base de datos, si el último paso no se completa, ésta quedaría en un estado inconsistente.

Para evitar estos casos existen las transacciones, si alguna de las acciones no se puede realizar todo se deshace a su estado inicial antes de la transacción.

Estos posibles errores son exponenciales a la simultaneidad del acceso a la información.

***

## Propiedades ACID
Estas son las características de una transacción:
- Atomicity (atomicidad): un cambio debe completarse en su totalidad o no modificar nada en absoluto.
- Consistency (consistencia): la base de datos debe quedar en un estado válido.
- Isolation (aislamiento): un cambio no debe afectar a otros cambios que se estén ejecutando al mismo tiempo sobre la base de datos.
- Durability (durabilidad): el cambio debe persistir, aunque se produzcan fallos en la base de datos o el sistema completo. 

## Commit y Rollback
Una transacción abierta (datos sucios) puede finalizar de dos formas:
- Si todo ha ido correctamente se realiza el **commit** para persistir los cambios y así dejar la base de datos actualizada en un estado consistente.
- Cualquier acción que no se pueda completar correctamente provoca un **rollback** y anula cualquier cambio realizado por el resto de acciones. La base de datos queda en el estado previo antes de la transacción

## Bloqueos y concurrencia
Otro tema importante relacionado con las transacciones es la gestión de la concurrencia y los bloqueos.
Para controlar el comportamiento de las transacciones en estos casos se definen diferentes niveles de aislamiento de una transacción:
- Serializable: Este es el nivel de aislamiento más alto. Especifica que todas las transacciones ocurran de modo aislado, dicho de otro modo, como si todas las transacciones se ejecutaran de modo serie (una tras otra). La sensación de ejecución simultánea de dos o más transacciones que perciben los usuarios sería una ilusión producida por el SGBD. Implementación basada en bloqueos.
- Lecturas repetibles: En este nivel de aislamiento, un SGBDR que implemente el control de concurrencia basado en bloqueos mantiene los bloqueos de lectura y escritura, de los datos seleccionados, hasta el final de la transacción.
- Lecturas comprometidas: Es igual al anterior, con la diferencia de que tan pronto como la operación SELECT acaba, los bloqueos de lectura se cancelan.
- Lecturas no comprometidas: Este es el menor nivel de aislamiento. En él se permiten las lecturas sucias, por lo que una transacción puede ver cambios no cometidos aún por otra transacción.

## Tipos de transacciones
De confirmación automática: El gestor de datos inicia una transacción automáticamente por cada operación que actualice datos. De este modo mantiene siempre la consistencia de la base de datos, aunque puede generar bloqueos.
Implícitas: Cuando el gestor de datos comienza una transacción automáticamente cada vez que se produce una actualización de datos, pero el que dicha transacción se confirme o se deshaga, lo debe indicar el programador.
Explícitas: Son las que iniciamos nosotros "a mano" mediante instrucciones SQL. Somos nosotros, los programadores, los que indicamos qué operaciones van a abarcar.
- Transacciones distribuidas: Aquella en la que intervienen dos o más recursos transaccionales de forma coordinada. Los dos estándares que los soportan son:
- Transaccionalidad distribuida XA
- Transaccionalidad WS-Transaction


## @Transactional

Excepciones: Commit y Rollback
Spring es el encargado de hacer el commit de la transacción, de forma automática, al finalizar la misma sin errores. 
Ante un error de tipo RuntimeException, Spring hará rollback automáticamente de la transacción, incluso aunque el programador capture la excepción. Si el error que se lanza es de tipo Exception, Spring no hará rollback. 
Al igual que muchas cosas, estos detalles son configurables. Podemos hacer a través de la configuración que ante ciertos tipos de excepciones runtime no se haga rollback automático. Justo el comportamiento contrario podemos aplicar para las excepciones de tipo excepción, se puede programar que ante ciertas excepciones de este tipo se haga un rollback automático. 


***
#### Enlaces de interés
- https://www.baeldung.com/java-transactions
- https://www.baeldung.com/spring-transactional-propagation-isolation
- https://www.apascualco.com/spring-boot/spring-transactional/
- https://danielme.com/2023/02/22/curso-spring-data-jpa-transacciones-propagacion-excepciones/