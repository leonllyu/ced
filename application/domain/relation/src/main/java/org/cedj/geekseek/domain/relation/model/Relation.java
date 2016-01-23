/*
 * Licensed by the authors under the Creative Commons
 * Attribution-ShareAlike 2.0 Generic (CC BY-SA 2.0)
 * License:
 *
 * http://creativecommons.org/licenses/by-sa/2.0/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.cedj.geekseek.domain.relation.model;

import java.io.Serializable;
import java.util.Date;

public class Relation {

    private Key key;
    private Date created;

    public Relation(String sourceId, String targetId, String type) {
        this.key = new Key(sourceId, targetId, type);
        this.created = new Date();
    }

    public String getSourceId() {
        return key.sourceId;
    }

    public String getTargetId() {
        return key.targetId;
    }

    public String getType() {
        return key.type;
    }

    public Date getCreated() {
        return (Date) created.clone();
    }

    private static class Key implements Serializable {

        private static final long serialVersionUID = 1L;

        private String sourceId;

        private String targetId;

        private String type;

        private Key(String sourceId, String targetId, String type) {
            this.sourceId = sourceId;
            this.targetId = targetId;
            this.type = type;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((sourceId == null) ? 0 : sourceId.hashCode());
            result = prime * result + ((targetId == null) ? 0 : targetId.hashCode());
            result = prime * result + ((type == null) ? 0 : type.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Key other = (Key) obj;
            if (sourceId == null) {
                if (other.sourceId != null)
                    return false;
            } else if (!sourceId.equals(other.sourceId))
                return false;
            if (targetId == null) {
                if (other.targetId != null)
                    return false;
            } else if (!targetId.equals(other.targetId))
                return false;
            if (type != other.type)
                return false;
            return true;
        }
    }
}
