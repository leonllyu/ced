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
package org.cedj.geekseek.domain.relation.test.model;

import java.io.Serializable;

import org.cedj.geekseek.domain.model.Identifiable;

public class SourceObject implements Identifiable, Serializable {

    private static final long serialVersionUID = 1L;

    public String id;

    public SourceObject(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
