/*
 * Copyright 2014 Higher Frequency Trading http://www.higherfrequencytrading.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.openhft.chronicle.map;


import java.io.Closeable;
import java.io.IOException;

final class Replicators {

    private Replicators() {
    }

    static Replicator tcp(final TcpReplicationReplicatorConfig replicationConfig) {
        return new Replicator() {

            @Override
            protected Closeable applyTo(ChronicleMapBuilder builder,
                                        Replica map, Replica.EntryExternalizable entryExternalizable)
                    throws IOException {
                return new TcpReplicator(map, entryExternalizable, replicationConfig,
                        builder.entrySize());
            }
        };
    }

    static Replicator udp(
            final UdpReplicationReplicatorConfig replicationConfig) {
        return new Replicator() {

            @Override
            protected Closeable applyTo(ChronicleMapBuilder builder,
                                        Replica map, Replica.EntryExternalizable entryExternalizable)
                    throws IOException {
                return new UdpReplicator(map, entryExternalizable, replicationConfig,
                        builder.entrySize());
            }
        };
    }
}
