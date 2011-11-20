/**
 * Copyright (c) 2002-2011 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.server.smack.core;

import org.neo4j.server.smack.serialization.DeserializationFactory;
import org.neo4j.server.smack.serialization.Deserializer;

import com.lmax.disruptor.WorkHandler;


public class DeserializationHandler implements WorkHandler<RequestEvent> {

    DeserializationFactory deserializationFactory = new DeserializationFactory();
    
    public void onEvent(final RequestEvent event)
            throws Exception {
        Deserializer d = deserializationFactory.getDeserializer(event.getContent());
        event.setDeserializedContent(event.getEndpoint().getDeserializationStrategy().deserialize(d));
    }

}